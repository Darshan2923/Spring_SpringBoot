@Service
public class ProducerService{
//      // Async
//      public void sendToKafka(final MyOutputData data) {
//     final ProducerRecord<String, String> record = createRecord(data);

//     CompletableFuture<SendResult<String, String>> future = template.send(record);
//     future.whenComplete((result, ex) -> {
//         if (ex == null) {
//             handleSuccess(data);
//         }
//         else {
//             handleFailure(data, record, ex);
//         }
//     });

     // Blocking 
     public void sendToKafka(final MyOutputData data) {
    final ProducerRecord<String, String> record = createRecord(data);

    try {
        template.send(record).get(10, TimeUnit.SECONDS);
        handleSuccess(data);
    }
    catch (ExecutionException e) {
        handleFailure(data, record, e.getCause());
    }
    catch (TimeoutException | InterruptedException e) {
        handleFailure(data, record, e);
    }
}
}
