package projects.service;

import java.util.List;
import java.util.NoSuchElementException;
import projects.dao.ProjectDao;
import projects.entity.Project;
import projects.exception.DbException;

/**
 * @author Promineo
 */

public class ProjectService {
	private ProjectDao projectDao = new ProjectDao();

	
	/**
	* Method that calls DAO class to insert a project row	
 	* The Project object with newly generated primary key value
	*/	
	public Project addProject(Project project) {
		return projectDao.insertProject(project);
	}

	
	/**
	 * Method that calls DAO class to fetch all columns of project table
	 */
	public List<Project> fetchAllProjects() {
		return projectDao.fetchAllProjects();
  }
	
	
	/**
	 * Method that calls DAO class specific column of project table
	 * based on listed project_id
	 */	
	public Project fetchProjectById(Integer projectId) {	
			return projectDao.fetchProjectById(projectId).orElseThrow(
			() -> new NoSuchElementException(
			"Project with project ID=" + projectId + " does not exist."));
	}
	
	
	/**
	 * Project object is passed as a parameter. The DAO method returns a boolean 
	 * that indicates whether the UPDATE operation was successful. 
	 * If false, throws a Db Exception that project does not exist. 	
	 */
	public void modifyProjectDetails(Project project) {
		if(!projectDao.modifyProjectDetails(project)) {
			throw new DbException("Project with ID="
					+ project.getProjectId() + " does not exist.");
		}
	}

	
	/**
	 * Project object is passed as a parameter. The DAO method returns a boolean 
	 * that indicates whether the DELETE operation was successful. 
	 * If false, throws a Db Exception that project does not exist. 	
	 */
	
	public void deleteProject(Integer projectId) {
		if(!projectDao.deleteProject(projectId)) {
		throw new DbException("Project with ID=" + projectId + " does not exist");
		}
	}
 
}
