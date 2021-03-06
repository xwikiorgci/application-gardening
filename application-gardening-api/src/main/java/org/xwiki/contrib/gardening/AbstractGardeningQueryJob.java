/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.contrib.gardening;

import org.xwiki.job.AbstractJob;
import org.xwiki.job.Job;
import org.xwiki.job.event.status.JobStatus;
import org.xwiki.stability.Unstable;

/**
 * The {@link AbstractGardeningQueryJob} is responsible for fetching a list of documents at the wiki or the farm level and then
 * return those documents in its {@link org.xwiki.job.event.status.JobStatus} so that they can be transmitted
 * to {@link AbstractGardeningActionJob}, that will apply actions on those documents.
 *
 * @version $Id$
 * @since 1.0
 */
@Unstable
public abstract class AbstractGardeningQueryJob extends
        AbstractJob<GardeningQueryJobRequest, GardeningQueryJobStatus>
{
    /**
     * The suffix of the job that should be used by subclasses when defining their job type.
     */
    public static final String JOB_TYPE_SUFFIX = "GardeningQueryJob";

    @Override
    protected GardeningQueryJobStatus createNewStatus(GardeningQueryJobRequest request)
    {
        Job currentJob = this.jobContext.getCurrentJob();
        JobStatus currentJobStatus = currentJob != null ? currentJob.getStatus() : null;
        return new GardeningQueryJobStatus(currentJob.getType(), request, currentJobStatus,
                this.observationManager, this.loggerManager);
    }
}
