package org.sujinde.beforenood;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TestListView extends Activity {

	ListView lvStringArray;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_list_view);
		lvStringArray=(ListView)findViewById(R.id.lvStringArray);
		String[] data={"sb","erbi","naocan"};
		ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1	, data);
		lvStringArray.setAdapter(adapter);
	}

}
