package in.nareshit.raghu.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import in.nareshit.raghu.listener.MyJobListener;
import in.nareshit.raghu.processor.MyItemProcessor;
import in.nareshit.raghu.reader.MyItemReader;
import in.nareshit.raghu.writer.MyItemWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	//7. Job
	@Bean
	public Job jobA() {
		return jf.get("jobA")
				.incrementer(new RunIdIncrementer())
				.listener(new MyJobListener())
				.start(stepA())
				//.next(stepB())
				//.next(stepC())
				.build();
	}
	
	//6. JobBuilderFactory
	@Autowired
	private JobBuilderFactory jf;
	
	//5. Step
	@Bean
	public Step stepA() {
		return sf.get("stepA") //step name
				.<String,String>chunk(2) //input,output,chunk size
				.reader(reader()) //reader object
				.processor(processor()) //processor object
				.writer(writer()) //writer object
				.build(); //create step
	}
	
	//4. StepBuilderFactory
	@Autowired
	private StepBuilderFactory sf;
	
	//3. ItemWriter
	@Bean
	public ItemWriter<String> writer() {
		return new MyItemWriter();
	}
	
	//2. ItemProcessor
	@Bean
	public ItemProcessor<String,String> processor() {
		return new MyItemProcessor();
	}

	//1. ItemReader
	@Bean
	public ItemReader<String> reader() {
		return new MyItemReader();
	}
}
