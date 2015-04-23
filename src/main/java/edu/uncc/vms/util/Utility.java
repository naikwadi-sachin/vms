package edu.uncc.vms.util;

import org.springframework.stereotype.Component;

@Component
public class Utility {

	public boolean isEmpty(String str) {
		if (str != null) {
			if (str.length() == 0)
				return true;
			else
				return false;
		}
		return true;
	}
}
