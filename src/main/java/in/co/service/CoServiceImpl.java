package in.co.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.entity.CoNoticeEntity;
import in.co.entity.EligEntity;
import in.co.repo.CoNoticeRepo;
import in.co.repo.EligRepo;
import in.co.utils.EmailUtils;

@Service
public class CoServiceImpl implements CoService {
	@Autowired
	private CoNoticeRepo noticeRepo;
	@Autowired
	private EligRepo eligRepo;
	@Autowired
	private EmailUtils emailutils;

	public void processPendingTriggers() {
		List<CoNoticeEntity> records = noticeRepo.findByNoticeStatus("P");
		for (CoNoticeEntity entity : records) {
			processEachRecord(entity);
		}

	}

	public void processEachRecord(CoNoticeEntity entity) {
		Long caseNum = entity.getCaseNum();
		EligEntity elig = eligRepo.findByCaseNum(caseNum);
		String planStatus = elig.getPlanStatus();
		File pdfFile = null;
		if ("AP".equals(planStatus)) {
			generateApprovedNotice(elig);
		} else if ("DN".equals(planStatus)) {
			generateDeniedNotice(elig);
		}
		String fileUrl = storePdfInS3(pdfFile);
		boolean isUpdate = updateProcessRecord(entity, fileUrl);
         if(isUpdate) {
        	 emailutils.sendEmail("", "", "", pdfFile);
         }
	}

	private String storePdfInS3(File pdfFile) {
		return null;
		// TODO Auto-generated method stub

	}

	private boolean updateProcessRecord(CoNoticeEntity entity, String fileUrl) {
		entity.setNoticeStatus("C");
		entity.setFileUrl(fileUrl);
		noticeRepo.save(entity);
		return true;
		// TODO Auto-generated method stub

	}

	private File generateDeniedNotice(EligEntity elig) {
		// TODO Auto-generated method stub
		return null;
	}

	private File generateApprovedNotice(EligEntity elig) {
		// TODO Auto-generated method stub
		return null;

	}

}
