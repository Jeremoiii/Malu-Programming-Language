package shared;

import generic.DataStructures.List;
import utils.QuickSort;

import java.util.Collections;

public class Projects {
    private List<ProjectFile> projects;
    private List<NetProjectFile> netProjects;

    public Projects() {
        this.projects = new List<>();
        this.netProjects = new List<>();
    }

    public void addProject(ProjectFile project) {
        this.projects.add(project);
    }

    public List<ProjectFile> getProjects() {
        return this.projects;
    }

    public int getListSize() {
        int size = 0;
        this.projects.toFirst();

        while (this.projects.hasAccess()) {
            size++;
            this.projects.next();
        }

        return size;
    }

    public void sortProjects() {
        QuickSort.sort(this.netProjects, 0, this.netProjects.size() - 1);
    }

    public void addNetProject(NetProjectFile project) {
        this.netProjects.add(project);
    }

    public List<NetProjectFile> getNetProjects() {
        return this.netProjects;
    }

    public int getNetListSize() {
        int size = 0;
        this.netProjects.toFirst();

        while (this.netProjects.hasAccess()) {
            size++;
            this.netProjects.next();
        }

        return size;
    }
}
