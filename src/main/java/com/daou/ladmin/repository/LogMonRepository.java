package com.daou.ladmin.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.daou.ladmin.entity.LogMon;
import com.daou.ladmin.entity.LogMonId;

public interface LogMonRepository extends JpaRepository<LogMon, LogMonId>, JpaSpecificationExecutor<LogMon> {

	public static Specification<LogMon> getSenderName(String senderName) {
		return new Specification<LogMon>() {
			@Override
			public Predicate toPredicate(Root<LogMon> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("senderEmail"), senderName);
			}
		};
	}

	//public static
}
