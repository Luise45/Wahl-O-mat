package htw.gruppe.backend.service;

/**
 * Interface fuer den Service 'ResetService' zur generierung der Email
 */
public interface ResetInterface {
    void sendEmail(String to, String subject, String text);
}
