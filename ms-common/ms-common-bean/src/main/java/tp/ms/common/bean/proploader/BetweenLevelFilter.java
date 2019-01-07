package tp.ms.common.bean.proploader;

import java.util.ArrayList;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;

public class BetweenLevelFilter extends AbstractMatcherFilter<ILoggingEvent> {
	
	Level level;
	
	ArrayList<Level> levelMappers = new ArrayList<Level>(2);
		
	@Override
	public FilterReply decide(ILoggingEvent event) {

		 if (!isStarted()) {
			 return FilterReply.NEUTRAL;
		 }
		 if(levelMappers.contains(event.getLevel()) || event.getLevel().equals(level)) {
			 return onMatch;
		 } else {
			 return onMismatch;
		 }
	}

    public void setLevel(Level level) {
    	levelMappers.clear();
    	if(Level.ALL.equals(level)) {
			levelMappers.add(Level.ALL);
			levelMappers.add(Level.TRACE);
    	}else if(Level.INFO.equals(level)) {
			levelMappers.add(Level.DEBUG);
			levelMappers.add(Level.INFO);
    	}else if(Level.ERROR.equals(level)) {
			levelMappers.add(Level.ERROR);
			levelMappers.add(Level.WARN);
    	}
        this.level = level;
    }
    
    public void start() {
        if (this.level != null) {
            super.start();
        }
    }
}
