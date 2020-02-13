package br.com.fiap.librarybatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class LibraryBatchApplication {

	Logger logger = LoggerFactory.getLogget(LibraryBatchApplication.class.getName());

	@Bean
	public Tasklet deleteFile(@Value("${file.path}") String path) {
		return (contribution, chunkContext)->{
			File file = Paths.get(path).toFile;
			if (file.delete()) {
				logger.info("Arquivo deletado");
			}
			return RepeatStatus.FINISHED;
		};
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Step deleteStep(StepBuilderFactory stepBuilderFactory,
						   Tasklet tasklet) {
		return stepBuilderFactory.get("delete file step")
				.tasklet(tasklet)
				.build();
	}

	@Bean
	public Job deleteJob(JobBuilderFactory jobBuilderFactory,
						 Step step) {
		return jobBuilderFactory.get("delete job")
				.start(step)
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryBatchApplication.class, args);
	}

}
