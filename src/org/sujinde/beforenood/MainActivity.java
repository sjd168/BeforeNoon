package org.sujinde.beforenood;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {

	ProgressBar pbBood;
	Button btnDo;
	Button btnCapture;
	ImageView ivPhoto;
	ListView lvStringArray;

	Uri imageUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();
		initData();
		MyListeners();
		TestDatabases();
	}

	public void initViews() {
		pbBood = (ProgressBar) findViewById(R.id.pbBood);
		lvStringArray = (ListView) findViewById(R.id.lvStringArray);
		btnDo = (Button) findViewById(R.id.btnDo);
		btnCapture = (Button) findViewById(R.id.btnCapture);
		ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
	}

	public void initData() {
		wrapListView();
	}

	public void MyListeners() {
		btnDo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int progress = pbBood.getProgress();
				progress += 10;
				pbBood.setProgress(progress);
				if (pbBood.getVisibility() == View.GONE) {
					pbBood.setVisibility(View.VISIBLE);
				} else {
					pbBood.setVisibility(View.GONE);
				}
			}
		});

		btnCapture.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				File outputImage = new File(Environment.getExternalStorageDirectory(), "image" + ".jpg");
				try {
					if (outputImage.exists()) {
						outputImage.delete();
					}
					outputImage.createNewFile();
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				imageUri = Uri.fromFile(outputImage);
				Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(intent, 1);
			}
		});

	}

	public void TestDatabases() {
		MyDatabaseUtil myDatabaseUtil = new MyDatabaseUtil(getApplicationContext(), "sujinde.db", null, 2, null);
		myDatabaseUtil.getWritableDatabase();
	}

	public void wrapListView() {
		String[] data = { "sb", "erbi", "naocan" };
		ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,
				data);
		lvStringArray.setAdapter(adapter);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case 1:
			Log.d("fuck", "resultCode :" + resultCode + "requestCode:" + requestCode);
			if (requestCode == 1) {
				try {

					if (imageUri == null) {
						Log.d("fuck", "imageUri is sb");
					} else {
						Log.d("fuck", "imageUri is not a sb");
					}
					Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
					ivPhoto.setImageBitmap(bitmap);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;

		default:
			break;
		}

	}

	public void TestAlertDialog() {
		AlertDialog.Builder myDialog = new AlertDialog.Builder(MainActivity.this);
		myDialog.setTitle("Fuck");
		myDialog.setMessage("What the hell ?");
		myDialog.setCancelable(false);
		myDialog.setPositiveButton("Positive", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "You click positiveButton", Toast.LENGTH_SHORT).show();
			}
		});
		myDialog.setNegativeButton("Negative", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "You click negativeButton", Toast.LENGTH_SHORT).show();
			}
		});
		myDialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
