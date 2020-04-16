package MyUtilities;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class java

{
	// ---( internal utility methods )---

	final static java _instance = new java();

	static java _newInstance() { return new java(); }

	static java _cast(Object o) { return (java)o; }

	// ---( server methods )---




	public static final void addLongIntegers (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(addLongIntegers)>> ---
		// @sigtype java 3.5
		// [i] field:0:required longInt1
		// [i] field:0:required longInt2
		// [o] field:0:required sumOfLongInts
			
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	longInt1 = IDataUtil.getString( pipelineCursor, "longInt1" );
			String	longInt2 = IDataUtil.getString( pipelineCursor, "longInt2" );
		pipelineCursor.destroy();
		
		long int1 = Integer.parseInt(longInt1);
		long int2 = Integer.parseInt(longInt2);
		
		long sumOfLongInts = int1 + int2;
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "sumOfLongInts", sumOfLongInts );
		pipelineCursor_1.destroy();
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---

	
	// --- <<IS-END-SHARED>> ---
}

