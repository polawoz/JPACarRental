package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.EmployeeEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
@Transactional
// @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OptimisticLockingTest {

	@Autowired
	private EmployeeDao employeeDao;

	@Test
	public void testShouldNotAllowToOverwriteEntity() {

		// given
		String position = "MANAGER";
		String lastName = "Kowalczyk";
		String firstName = "Patryk";
		Date dateOfBirth = Date.valueOf("1989-12-03");

		EmployeeEntity employeeBeforeSave = EmployeeEntity.builder().position(position).lastName(lastName)
				.firstName(firstName).dateOfBirth(dateOfBirth).build();

		// First user write and read
		EmployeeEntity employeeAfterSave = employeeDao.save(employeeBeforeSave);
		employeeDao.flush();
		employeeDao.detach(employeeAfterSave);

		// Second user read and write
		EmployeeEntity employeeSecondUserEdit = employeeDao.findOne(employeeAfterSave.getId());
		employeeSecondUserEdit.setFirstName("Pawel");
		employeeDao.save(employeeSecondUserEdit);
		employeeDao.flush();

		// First user continues to perform changes
		employeeAfterSave.setFirstName("Jan");

		boolean exceptionThrown = false;

		// when
		try {
			employeeDao.update(employeeAfterSave);
		} catch (OptimisticLockingFailureException e) {
			exceptionThrown = true;
		}

		// then
		assertTrue(exceptionThrown);
		EmployeeEntity employeeAfterAllChanges = employeeDao.findOne(employeeAfterSave.getId());
		assertEquals("Pawel", employeeAfterAllChanges.getFirstName());
		assertEquals(employeeAfterSave.hashCode(), employeeBeforeSave.hashCode());

	}

}
