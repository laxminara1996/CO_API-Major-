package in.co.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.co.entity.EligEntity;

public interface EligRepo extends JpaRepository<EligEntity, Integer> {
public EligEntity findByCaseNum(Long caseNum);
}
