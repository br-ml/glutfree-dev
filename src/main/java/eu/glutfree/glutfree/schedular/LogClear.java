//package eu.glutfree.glutfree.schedular;
//
//import eu.glutfree.glutfree.repository.LogRepository;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class LogClear {
//
//    private final LogRepository logRepository;
//
//    public LogClear(LogRepository logRepository) {
//        this.logRepository = logRepository;
//    }
//
//    @Scheduled(cron = "0 0 0 1 * *")
//    public void clearLogsEveryMonth() {
//
//        System.out.println("clearing now");
//        this.logRepository.deleteAll();
//
//    }
//
//}
