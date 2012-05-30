/*
 * Copyright 2011 SpringSource, a division of VMware, Inc
 * 
 * andrew - Initial API and implementation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springsource.sts.maven.internal.legacyconversion;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;

import com.springsource.sts.maven.MavenCorePlugin;

/**
 * Listens for legacy maven projects appearing in the workspace
 * 
 * @author Andrew Eisenberg
 * @since 2.8.0
 */
public class LegacyProjectListener implements IResourceChangeListener, IM2EConstants {

    public static final LegacyProjectListener LISTENER = new LegacyProjectListener();

    /**
     * @return only do check if the new m2eclipse is present and the user has not explicitly disabled the check
     */
    private boolean shouldPerformCheck() {
    	return MavenCorePlugin.IS_M2ECLIPSE_PRESENT && ! MavenCorePlugin.getDefault().getPreferenceStore().getBoolean(DONT_AUTO_CHECK);
    }

    public void resourceChanged(IResourceChangeEvent event) {
        if (event.getType() == IResourceChangeEvent.POST_CHANGE) {
            List<IProject> projects = getProjects(event.getDelta());
            if (!projects.isEmpty() && shouldPerformCheck()) {
                LegacyProjectsJob job = new LegacyProjectsJob(projects, false, true);
                job.schedule();
            }
        }
    }

    private List<IProject> getProjects(IResourceDelta delta) {
        final List<IProject> projects = new ArrayList<IProject>();
        try {
            delta.accept(new IResourceDeltaVisitor() {
                public boolean visit(IResourceDelta delta) throws CoreException {
                    if (delta.getKind() == IResourceDelta.ADDED && delta.getResource().getType() == IResource.PROJECT) {
                        IProject project = (IProject) delta.getResource();
                        if (project.isAccessible() && project.hasNature(OLD_NATURE)) {
                            projects.add(project);
                        }
                    }
                    // only continue for the workspace root
                    return delta.getResource().getType() == IResource.ROOT;
                }
            });
        } catch (CoreException e) {
            MavenCorePlugin.getDefault().getLog().log(e.getStatus());
        }
        return projects;
    }

}
