package in.nareshit.raghu.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class MyItemReader implements ItemReader<String> {

	String inputs[] = {"hello","this","is","raghu","nareshit","batch"};
	int position;
	
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if(position<inputs.length) {
			return inputs[position++];
		}else {
			position = 0;
		}
		return null;
	}
}
