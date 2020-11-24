package com.pd.springboot.service;

import javax.inject.Named;

import com.pd.businessobject.ProposalFO;
import com.pd.businessobject.ProposalVO;
import com.pd.springboot.dao.IProposalDao;

@Named
public class ProposalService extends ServiceAdapter<ProposalFO, ProposalVO, IProposalDao> {
}
