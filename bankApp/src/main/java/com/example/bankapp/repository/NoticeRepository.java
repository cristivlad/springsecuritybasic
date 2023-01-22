package com.example.bankapp.repository;

import com.example.bankapp.model.Notice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long> {
    @Query(value = "from Notice n where current_date between n.noticeBegDt and n.noticeEndDt")
    List<Notice> findAllActiveNotices();
}
