import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import com.alibaba.ververica.cdc.debezium.StringDebeziumDeserializationSchema;
import com.alibaba.ververica.cdc.connectors.mysql.MySQLSource;
public class Test {
    public static void main(String[] args) throws Exception {
        SourceFunction<String> sourceFunction = MySQLSource.<String>builder()
                .hostname("hadoop101")
                .port(3306)
                .databaseList("realtime-gmall") // monitor all tables under inventory database
                .tableList("order_info")
                .username("root")
                .password("root")
                .deserializer(new StringDebeziumDeserializationSchema()) // converts SourceRecord to String/
                .build();

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        DataStreamSource<String> source = env.addSource(sourceFunction);
        source.print();
        env.execute();

    }
}
