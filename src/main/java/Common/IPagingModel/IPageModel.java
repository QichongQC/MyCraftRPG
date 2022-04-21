package Common.IPagingModel;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 依赖于MybatisPlus的分页功能
 * @param <T>
 */
public class IPageModel<T> extends Page<T> {


    public long getBarStart() {
        //当前页码
        long current = this.getCurrent();
        //如果current -1 测试低页码
        long result=current-1>0 ? current-1:1;

        return result;
    }

    public long getBarEnd() {
        long current = this.getCurrent();
        long result=current+1 > this.getPages()? this.getPages() : current+1;
        return result;
    }
}
