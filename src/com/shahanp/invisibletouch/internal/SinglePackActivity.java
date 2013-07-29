package com.shahanp.invisibletouch.internal;

import android.view.View;
import com.shahanp.invisibletouch.R;
import com.shahanp.invisibletouch.utils.Log;

public abstract class SinglePackActivity extends BaseActivity {

	@Override
	protected void init() {
		setContentView(R.layout.screen_single_pack);
		View _one_one = findViewById(R.id.item_one_one);
		_one_one.setClickable(true);
		_one_one.setOnClickListener(this);
		onAttachView(R.id.item_one_one, _one_one);
	}
	
	@Override
	public void onKeyTwo() {
		throw new UnsupportedOperationException("Single pack activities does not support this operation");
	}

	@Override
	public void onKeyThree() {
		throw new UnsupportedOperationException("Single pack activities does not support this operation");
	}

	@Override
	public void onKeyFour() {
		throw new UnsupportedOperationException("Single pack activities does not support this operation");
	}

	@Override
	public void onKeyFive() {
		throw new UnsupportedOperationException("Single pack activities does not support this operation");
	}

	@Override
	public void onKeySix() {
		throw new UnsupportedOperationException("Single pack activities does not support this operation");
	}

	@Override
	public void onLongKeyTwo() {
		throw new UnsupportedOperationException("Single pack activities does not support this operation");		
	}

	@Override
	public void onLongKeyThree() {
		throw new UnsupportedOperationException("Single pack activities does not support this operation");		
	}

	@Override
	public void onLongKeyFour() {
		throw new UnsupportedOperationException("Single pack activities does not support this operation");		
	}

	@Override
	public void onLongKeyFive() {
		throw new UnsupportedOperationException("Single pack activities does not support this operation");		
	}

	@Override
	public void onLongKeySix() {
		throw new UnsupportedOperationException("Single pack activities does not support this operation");		
	}

    @Override
    public void onPowerKeyShortPress() {
        Log.announce("Power short press", Log.Level.INFO);
    }

    @Override
    public void onPowerKeyLongPress() {
        Log.announce("Power Long press", Log.Level.INFO);
    }

}
