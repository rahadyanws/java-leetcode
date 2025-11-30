public class DataUsageTracker {
  private double monthlyQuota; // dalam GB
  private double usedData;     // dalam GB

  public DataUsageTracker(double monthlyQuota) {
    this.monthlyQuota = monthlyQuota;
    this.usedData = 0;
  }

  public void useData(double amount) {
    usedData += amount;
    double remainingData = monthlyQuota - usedData;
    double usagePercentage = (usedData / monthlyQuota) * 100;

    System.out.printf("Digunakan: %.2fGB, Sisa: %.2fGB (%.1f%%)%n",
        usedData, remainingData, usagePercentage);

    if (remainingData < monthlyQuota * 0.1) {
      System.out.println("PERINGATAN: Kuota data hampir habis!");
    }

    if (usedData > monthlyQuota) {
      System.out.println("PERINGATAN: Kuota data telah terlampaui!");
    }
  }

  public static void main(String[] args) {
    DataUsageTracker tracker = new DataUsageTracker(10.0); // 10GB quota
    tracker.useData(3.5);
    tracker.useData(5.0); // Seharusnya trigger peringatan hampir habis
    tracker.useData(2.0); // Seharusnya trigger peringatan terlampaui
  }
}