import java.util.concurrent.TimeUnit;

public class RateLimiter {
  private final long maxCapacity;
  private final long refillRatePerSecond;

  private double currentTokens;
  private long lastRefillTimestamp;

  public RateLimiter(long maxCapacity, long refillRatePerSecond) {
    this.maxCapacity = maxCapacity;
    this.refillRatePerSecond = refillRatePerSecond;
    this.currentTokens = maxCapacity;
    this.lastRefillTimestamp = System.nanoTime();
  }

  // Method utama: Thread-safe menggunakan 'synchronized'
  public synchronized boolean tryAcquire() {
    refill();
    if (currentTokens >= 1) {
      currentTokens -= 1;
      return true; // Request diizinkan
    }
    return false; // Request ditolak (Rate Limited)
  }

  // Logika "Lazy Refill": Kita hanya isi ulang saat ada request masuk
  private void refill() {
    long now = System.nanoTime();
    long timeElapsed = now - lastRefillTimestamp;

    // Hitung berapa token yang harus ditambahkan berdasarkan waktu yang berlalu
    // Rumus: (waktu berlalu dalam detik) * rate
    double tokensToAdd = (timeElapsed / 1_000_000_000.0) * refillRatePerSecond;

    if (tokensToAdd > 0) {
      // Isi token, tapi jangan melebihi kapasitas maksimum
      currentTokens = Math.min(maxCapacity, currentTokens + tokensToAdd);
      lastRefillTimestamp = now;
    }
  }

  public static void main(String[] args) throws InterruptedException {
    // Buat limiter: Maksimal 3 request, isi ulang 2 token per detik
    RateLimiter limiter = new RateLimiter(3, 2);

    // Simulasi 10 request berturut-turut
    for (int i = 1; i <= 20; i++) {
      boolean accessGranted = limiter.tryAcquire();
      if (accessGranted) {
        System.out.println("Request " + i + ": DITERIMA ✅");
      } else {
        System.out.println("Request " + i + ": DITOLAK ⛔ (Rate Limited)");
      }

      // Jeda sedikit (400ms) antar request
      Thread.sleep(400);
    }
  }
}