package service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import domain.AgencyBidding;
import mapper.AgencyBiddingMapper;
import org.springframework.stereotype.Service;
import service.IAgencyBiddingService;

/**
 * @author lzp
 * @date 2023年11月14 13:45
 */

@Service
public class AgencyBiddingServiceImpl extends ServiceImpl<AgencyBiddingMapper, AgencyBidding> implements IAgencyBiddingService {
}
