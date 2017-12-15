package util.sendkey.jna;

import java.util.HashMap;
import java.util.Map;

import com.sun.jna.FromNativeContext;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIFunctionMapper;
import com.sun.jna.win32.W32APITypeMapper; 

@SuppressWarnings({ "unchecked", "serial" })
public interface W32API extends StdCallLibrary { 
	Map UNICODE_OPTIONS = new HashMap() { 
		/** * */ 
		private static final long serialVersionUID = 1L; 
		{ 
			put(OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE); 
			put(OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.UNICODE); 
		} 
	}; 
	
	Map ASCII_OPTIONS = new HashMap() { 
		{ 
			put(OPTION_TYPE_MAPPER, W32APITypeMapper.ASCII); 
			put(OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.ASCII); 
		} 
	}; 
	
	Map DEFAULT_OPTIONS = Boolean.getBoolean("w32.ascii") ? ASCII_OPTIONS : UNICODE_OPTIONS; 
	
	public static class HANDLE extends PointerType { 
		
		public Object fromNative(Object nativeValue, FromNativeContext context) { 
			Object o = super.fromNative(nativeValue, context);
			if (INVALID_HANDLE_VALUE.equals(o)) 
				return INVALID_HANDLE_VALUE; 
			return o; 
		} 
		
	} 
	
	public static class HWND extends HANDLE { 
		
	} 
	
	HANDLE INVALID_HANDLE_VALUE = new HANDLE() { 
		{ 
			super.setPointer(Pointer.createConstant(-1)); 
		} 
		
		public void setPointer(Pointer p) { 
			throw new UnsupportedOperationException("Immutable reference"); 
		} 
		
	};
}