package com.axcess.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axcess.model.ReversalAPIRequest;
import com.axcess.service.ReversalAPIService;

@Controller
public class TxnController {

	@Autowired
	ReversalAPIService reversalAPIService;

	@Autowired 
	GetBalanceService getBalanceService;

	@PostMapping(path="/txn")
	@ResponseBody
	public String postTransaction(@RequestParam(name="TxnId", required=false) String txnId, 
	@RequestParam(name="ReversalReasonCode",required=false) String reversalReasonCode, 
	@RequestParam (name="reverse", required=false) boolean reverse){
		
		String response="400";
		System.out.println("txn");
		if (reverse){
			System.out.println("reverse txn");
			
			List<ReversalAPIRequest> txnDtl=reversalAPIService.findByid(txnId);
			
			if (null!=txnDtl && txnDtl.size()>0){
				
				String status=String.valueOf(txnDtl.get(0).getStatus());
				
				if (status!=null && status.equalsIgnoreCase("SUCCESS")){
					
					String bankId=String.valueOf(txnDtl.get(0).getBankid());
					int points=Integer.parseInt(txnDtl.get(0).getAmount());
					
					int recCnt=getBalanceService.revUserPoints(bankId,points,"REVERSAL");
					
					if (recCnt>0){
						
						int updtCnt=reversalAPIService.revTxn(txnId, reversalReasonCode);
						
						if (updtCnt>0){
							response="200";
						}
						
					}
					
				}
				
			}
		}
	return response;	
		
	}
	

}

