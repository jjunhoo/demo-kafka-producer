import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoProducer {
    private final static Logger logger = LoggerFactory.getLogger(DemoProducer.class);
    private final static String TOPIC_NAME = "test"; // 토픽명
    private final static String BOOTSTRAP_SERVERS = "localhost:9092"; // 카프카 클러스터 정보

    public static void main(String[] args) {
        // 카프카 필수 옵션 설정
        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // KafkaProducer - StringSerializer -> String
        KafkaProducer<String, String> producer = new KafkaProducer<>(configs); // 프로듀서 인스턴스 생성

        // ProducerRecord
        String messageValue = "testMessage";
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, messageValue); // 토픽명, 메시지 내용
        producer.send(record); // 레코드 송신

        logger.info("{}", record);
        producer.flush(); // Accumulator 에 쌓인 데이터를 모두 강제 전송
        producer.close();
    }
}
