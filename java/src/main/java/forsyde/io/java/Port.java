/**
 * 
 */
package forsyde.io.java;

import javax.annotation.processing.Generated;

import lombok.Builder;
import lombok.ToString;

/**
 * @author rjordao
 *
 */
@Builder
@ToString
public class Port {

	public String identifier;
	public FType type;
	
}
