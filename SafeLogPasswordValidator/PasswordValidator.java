import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PasswordValidator {

    interface PasswordPolicy {
        ValidationResult validate(String password);
        String getDescription();
        int getWeight();
    }

    enum PasswordStrength {
        VERY_WEAK  (0,  3,  "VERY WEAK",   "[-]", "[ ][ ][ ][ ][ ]"),
        WEAK       (4,  5,  "WEAK",        "[~]", "[#][ ][ ][ ][ ]"),
        MODERATE   (6,  7,  "MODERATE",    "[*]", "[#][#][ ][ ][ ]"),
        STRONG     (8,  9,  "STRONG",      "[+]", "[#][#][#][ ][ ]"),
        VERY_STRONG(10, 99, "VERY STRONG", "[!]", "[#][#][#][#][#]");

        private final int    minScore, maxScore;
        private final String label, badge, meter;

        PasswordStrength(int mn, int mx, String label, String badge, String meter) {
            this.minScore = mn; this.maxScore = mx;
            this.label = label; this.badge = badge; this.meter = meter;
        }

        static PasswordStrength fromScore(int score) {
            for (PasswordStrength s : values())
                if (score >= s.minScore && score <= s.maxScore) return s;
            return VERY_WEAK;
        }

        String getLabel() { return label; }

        @Override
        public String toString() { return badge + " " + label + " " + meter; }
    }

    static class ValidationResult {
        private final boolean passed;
        private final String  ruleName;
        private final String  message;
        private final int     weightEarned;

        ValidationResult(boolean passed, String ruleName, String message, int weight) {
            this.passed       = passed;
            this.ruleName     = ruleName;
            this.message      = message;
            this.weightEarned = passed ? weight : 0;
        }

        boolean isPassed()        { return passed;       }
        String  getMessage()      { return message;      }
        String  getRuleName()     { return ruleName;     }
        int     getWeightEarned() { return weightEarned; }

        @Override
        public String toString() {
            return String.format("  [%s] %s", passed ? "PASS" : "FAIL", message);
        }
    }

    static abstract class ValidationRule implements PasswordPolicy {

        protected final String ruleName;
        protected final String description;
        protected final int    weight;

        protected ValidationRule(String ruleName, String description, int weight) {
            this.ruleName    = ruleName;
            this.description = description;
            this.weight      = weight;
        }

        protected abstract boolean check(String password);
        protected abstract String failureMessage(String password);

        @Override
        public final ValidationResult validate(String password) {
            boolean ok  = check(password);
            String  msg = ok ? description + " -- Passed" : failureMessage(password);
            return new ValidationResult(ok, ruleName, msg, weight);
        }

        @Override public String getDescription() { return description; }
        @Override public int    getWeight()      { return weight;      }
    }

    static class LengthRule extends ValidationRule {
        private final int minLen;

        LengthRule(int minLen) {
            super("LENGTH", "At least " + minLen + " characters long", 2);
            this.minLen = minLen;
        }

        @Override
        protected boolean check(String p) { return p.length() >= minLen; }

        @Override
        protected String failureMessage(String password) {
            return "Too short! Need " + minLen + ", got " + password.length() + " chars.";
        }
    }

    static class UppercaseRule extends ValidationRule {
        UppercaseRule() {
            super("UPPERCASE", "Contains at least one uppercase letter (A-Z)", 2);
        }

        @Override
        protected boolean check(String p) {
            for (int i = 0; i < p.length(); i++)
                if (Character.isUpperCase(p.charAt(i))) return true;
            return false;
        }

        @Override
        protected String failureMessage(String password) {
            return "Missing an uppercase letter (A-Z). Add at least one.";
        }
    }

    static class DigitRule extends ValidationRule {
        DigitRule() {
            super("DIGIT", "Contains at least one digit (0-9)", 2);
        }

        @Override
        protected boolean check(String p) {
            for (int i = 0; i < p.length(); i++)
                if (Character.isDigit(p.charAt(i))) return true;
            return false;
        }

        @Override
        protected String failureMessage(String password) {
            return "Missing a digit (0-9). Add at least one number.";
        }
    }

    static class SpecialCharRule extends ValidationRule {
        private static final String SPECIAL = "!@#$%^&*()-_=+[]{}|;:',.<>?/`~";

        SpecialCharRule() {
            super("SPECIAL", "Contains at least one special character (!@#$%...)", 3);
        }

        @Override
        protected boolean check(String p) {
            for (int i = 0; i < p.length(); i++)
                if (SPECIAL.indexOf(p.charAt(i)) >= 0) return true;
            return false;
        }

        @Override
        protected String failureMessage(String password) {
            return "Missing a special character (e.g. !@#$%^&*). Add one.";
        }
    }

    static class NoWhitespaceRule extends ValidationRule {
        NoWhitespaceRule() {
            super("NO_SPACE", "Must NOT contain spaces or whitespace", 1);
        }

        @Override
        protected boolean check(String p) {
            for (int i = 0; i < p.length(); i++)
                if (Character.isWhitespace(p.charAt(i))) return false;
            return true;
        }

        @Override
        protected String failureMessage(String password) {
            return "Password must not contain spaces or whitespace.";
        }
    }

    static class BlacklistRule extends ValidationRule {
        private static final String[] BANNED = {
            "password", "12345678", "qwerty123", "admin123",
            "welcome1", "letmein1", "password1", "abc12345"
        };

        BlacklistRule() {
            super("BLACKLIST", "Must not be a commonly used password", 1);
        }

        @Override
        protected boolean check(String p) {
            String lower = p.toLowerCase();
            for (String b : BANNED) if (lower.equals(b)) return false;
            return true;
        }

        @Override
        protected String failureMessage(String password) {
            return "This is a banned common password. Choose something unique.";
        }
    }

    static class PolicyFactory {
        private PolicyFactory() {}

        static PasswordValidator createBasicPolicy() {
            return new PasswordValidator()
                    .addRule(new LengthRule(8))
                    .addRule(new UppercaseRule())
                    .addRule(new DigitRule());
        }

        static PasswordValidator createStandardPolicy() {
            return new PasswordValidator()
                    .addRule(new LengthRule(10))
                    .addRule(new UppercaseRule())
                    .addRule(new DigitRule())
                    .addRule(new SpecialCharRule())
                    .addRule(new NoWhitespaceRule());
        }

        static PasswordValidator createStrictPolicy() {
            return new PasswordValidator()
                    .addRule(new LengthRule(14))
                    .addRule(new UppercaseRule())
                    .addRule(new DigitRule())
                    .addRule(new SpecialCharRule())
                    .addRule(new NoWhitespaceRule())
                    .addRule(new BlacklistRule());
        }

        static String policyLabel(String choice) {
            return switch (choice) {
                case "1" -> "Basic    [Length>=8  | Uppercase | Digit]";
                case "3" -> "Strict   [Length>=14 | Uppercase | Digit | Special | No-Space | Blacklist]";
                default  -> "Standard [Length>=10 | Uppercase | Digit | Special | No-Space]";
            };
        }
    }

    static class ValidationReport {
        private final String                 maskedPwd;
        private final List<ValidationResult> results;
        private final int                    passed, failed, score, maxScore;
        private final PasswordStrength       strength;
        private final boolean                isValid;

        ValidationReport(String pwd, List<ValidationResult> results,
                         int passed, int failed, int score, int maxScore) {
            this.maskedPwd = mask(pwd);
            this.results   = results;
            this.passed    = passed; this.failed = failed;
            this.score     = score;  this.maxScore = maxScore;
            this.strength  = PasswordStrength.fromScore(score);
            this.isValid   = (failed == 0);
        }

        boolean          isValid()     { return isValid;  }
        PasswordStrength getStrength() { return strength; }

        private static String mask(String pw) {
            if (pw.length() <= 2) return "**";
            return pw.charAt(0) + "*".repeat(pw.length() - 2) + pw.charAt(pw.length() - 1);
        }

        void print() {
            System.out.println();
            System.out.println("  Password : " + maskedPwd);
            System.out.println("  Strength : " + strength);
            System.out.println("  Score    : " + score + " / " + maxScore + " pts");
            System.out.println("  Rules    : " + passed + " passed,  " + failed + " failed");
            System.out.println();
            System.out.println("  Rule-by-Rule Feedback:");
            for (ValidationResult r : results) System.out.println(r);
            System.out.println();
            if (isValid)
                System.out.println("  [OK]  All rules passed -- Password Accepted!");
            else
                System.out.printf("  [!!]  %d rule(s) failed. Fix the issues above.%n", failed);
        }
    }

    static class UserAccount {
        private final String       empId, fullName, department;
        private       boolean      passwordSet    = false;
        private       int          failedAttempts = 0;
        private final List<String> history        = new ArrayList<>();

        UserAccount(String id, String name, String dept) {
            this.empId = id; this.fullName = name; this.department = dept;
        }

        void recordFailed(int n) {
            failedAttempts++;
            history.add("Attempt #" + n + " - FAILED  (cumulative failures: " + failedAttempts + ")");
        }

        void recordSuccess(int n) {
            passwordSet = true;
            history.add("Attempt #" + n + " - SUCCESS");
        }

        String  getFullName()       { return fullName;       }
        boolean isPasswordSet()     { return passwordSet;    }
        int     getFailedAttempts() { return failedAttempts; }

        void printSummary() {
            System.out.println();
            System.out.println("  Account Setup Summary");
            System.out.println("  Employee ID  : " + empId);
            System.out.println("  Name         : " + fullName);
            System.out.println("  Department   : " + department);
            System.out.println("  Status       : " + (passwordSet ? "[OK] Password Set" : "[!!] Locked"));
            System.out.println("  Failed tries : " + failedAttempts);
            System.out.println();
            System.out.println("  Attempt History:");
            for (String h : history)
                System.out.println("    > " + h);
        }
    }

    static class AuditLogger {
        private static final AuditLogger INSTANCE = new AuditLogger();
        private final List<String> entries = new ArrayList<>();

        private AuditLogger() {}

        static AuditLogger getInstance() { return INSTANCE; }

        void record(String id, String event, boolean ok) {
            entries.add(String.format("[%-7s] Emp: %-10s | %s",
                                      ok ? "SUCCESS" : "FAILURE", id, event));
        }

        void info(String msg) {
            entries.add("[INFO   ] " + msg);
        }

        void printLog() {
            System.out.println();
            System.out.println("  Audit Log");
            for (String e : entries)
                System.out.println("  " + e);
        }
    }

    private final List<PasswordPolicy> rules = new ArrayList<>();

    PasswordValidator addRule(PasswordPolicy rule) {
        rules.add(rule);
        return this;
    }

    ValidationReport run(String password) {
        List<ValidationResult> results = new ArrayList<>();
        int score = 0, max = 0, pass = 0, fail = 0;

        for (PasswordPolicy rule : rules) {
            ValidationResult r = rule.validate(password);
            results.add(r);
            max += rule.getWeight();
            if (r.isPassed()) { score += r.getWeightEarned(); pass++; }
            else              { fail++; }
        }

        return new ValidationReport(password, results, pass, fail, score, max);
    }

    List<PasswordPolicy> getRules() { return Collections.unmodifiableList(rules); }

    public static void main(String[] args) {

        Scanner     sc     = new Scanner(System.in);
        AuditLogger logger = AuditLogger.getInstance();

        printBanner();
        logger.info("SafeLog application started.");

        System.out.print("  Employee ID  : ");
        String empId = sc.nextLine().trim();

        System.out.print("  Full Name    : ");
        String name  = sc.nextLine().trim();

        System.out.print("  Department   : ");
        String dept  = sc.nextLine().trim();

        UserAccount account = new UserAccount(empId, name, dept);
        logger.info("Session opened - Employee: " + empId + " | " + name);

        System.out.println();
        System.out.println("  Select Security Policy:");
        System.out.println("    1. Basic    -- Min  8 chars | 3 rules");
        System.out.println("    2. Standard -- Min 10 chars | 5 rules  (default)");
        System.out.println("    3. Strict   -- Min 14 chars | 6 rules");
        System.out.print("  Your choice (1/2/3) [Enter for Standard]: ");
        String choice = sc.nextLine().trim();

        PasswordValidator validator = switch (choice) {
            case "1" -> PolicyFactory.createBasicPolicy();
            case "3" -> PolicyFactory.createStrictPolicy();
            default  -> PolicyFactory.createStandardPolicy();
        };

        System.out.println("  Active Policy: " + PolicyFactory.policyLabel(choice));
        System.out.println();
        System.out.println("  Rules in this policy:");
        for (PasswordPolicy rule : validator.getRules())
            System.out.printf("    > %-50s weight +%d%n", rule.getDescription(), rule.getWeight());
        int  attempt = 0;
        boolean ok   = false;

        while (!ok) {
            attempt++;
            System.out.printf("%n  Attempt %d%n", attempt);
            System.out.print("  Enter new password: ");
            String pwd = sc.nextLine();

            ValidationReport report = validator.run(pwd);
            report.print();

            if (report.isValid()) {
                ok = true;
                account.recordSuccess(attempt);
                logger.record(empId, "Password set on attempt #" + attempt, true);
                System.out.println();
                System.out.println("  [SUCCESS] Welcome to SafeLog, " + account.getFullName() + "!");
                System.out.println("            Your account is now secured.");
            } else {
                account.recordFailed(attempt);
                logger.record(empId,
                    "Attempt #" + attempt + " failed | Strength: " +
                    report.getStrength().getLabel(), false);
            }
        }
        account.printSummary();
        logger.info("Session closed - Employee: " + empId);
        logger.printLog();

        sc.close();
    }

    static void printBanner() {
        System.out.println();
        System.out.println("Employee Password Validator");
        System.out.println();
    }
}