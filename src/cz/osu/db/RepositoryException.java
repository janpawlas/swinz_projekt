package cz.osu.db;

public class RepositoryException extends RuntimeException {
    public RepositoryException(String message, Throwable cause) {
        super( "DB operation error" + message, cause);
    }
}
