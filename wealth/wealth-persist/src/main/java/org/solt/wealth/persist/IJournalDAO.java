package org.solt.wealth.persist;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.solt.wealth.model.Journal;

public interface IJournalDAO {

	Journal getJournalById(Journal journal) throws SQLException;

	List<Journal> findJournal(Journal journal) throws SQLException;

	List<Journal> findJournalByParam(Map<String, Object> params) throws SQLException;

	Double getTotalJournal(Map<String, Object> params) throws SQLException;

	int insertJournal(Journal journal) throws SQLException;

	int updateJournal(Journal journal) throws SQLException;

	int deleteJournal(Journal journal) throws SQLException;

}
