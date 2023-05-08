package cart.exception;

import org.springframework.http.HttpStatus;

public class CartException extends RuntimeException {
  private final HttpStatus status;

  public CartException(final String message, final HttpStatus status) {
    super(message);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}