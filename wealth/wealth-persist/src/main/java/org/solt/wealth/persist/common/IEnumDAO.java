package org.solt.wealth.persist.common;

import java.util.List;

import org.solt.wealth.model.common.Enums;

public interface IEnumDAO {

	public Enums getEnumsById(Enums enums);

	public List<Enums> findEnumsByParam(Enums enums);
}
