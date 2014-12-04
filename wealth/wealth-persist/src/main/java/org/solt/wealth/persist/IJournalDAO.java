package org.solt.wealth.persist;

import java.util.List;
import java.util.Map;

import org.solt.wealth.model.Journal;

public interface IJournalDAO {

	Journal getJournalById(Journal journal);

	List<Journal> findJournal(Journal journal);

	List<Journal> findJournalByParam(Map<String, Object> params);

	Double getTotalJournal(Map<String, Object> params);

	int insertJournal(Journal journal);

	int updateJournal(Journal journal);

	int deleteJournal(Journal journal);

}
