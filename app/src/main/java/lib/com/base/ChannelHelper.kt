package lib.com.base

import com.kernel.lib.base.BaseApplication
import com.kernel.lib.helper.Helper
import com.meituan.android.walle.WalleChannelReader

/**
 * Created by hp on 2017/12/5.
 */

object ChannelHelper {

    fun getChannel(): String {
        var channel = "xxx"
        if (Helper.isEmpty(channel)) {
            channel = WalleChannelReader.getChannel(BaseApplication.instance)!!
        }
        return channel
    }

}
