package jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.core.util.ShardingUtil;
import org.springframework.stereotype.Component;

/**
 * @author yangxing
 * @date 2019/12/30
 */
@Component
public class ShardingJobHandler{
    /**
     * 2、分片广播任务
     */
    @XxlJob("shardingJobHandler")
    public ReturnT<String> shardingJobHandler(String param) throws Exception {

        // 分片参数
        ShardingUtil.ShardingVO shardingVO = ShardingUtil.getShardingVo();
        XxlJobLogger.log("分片参数：当前分片序号 = {}, 总分片数 = {}", shardingVO.getIndex(), shardingVO.getTotal());

        // 业务逻辑
        for (int i = 0; i < shardingVO.getTotal(); i++) {
            if (i == shardingVO.getIndex()) {
                XxlJobLogger.log("第 {} 片, 命中分片开始处理", i);
            } else {
                XxlJobLogger.log("第 {} 片, 忽略", i);
            }
        }

        return ReturnT.SUCCESS;
    }
}
