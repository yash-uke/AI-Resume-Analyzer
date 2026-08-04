import { Routes } from '@angular/router';

import { MainLayout } from './layouts/main-layout/main-layout';

import { Dashboard } from './features/dashboard/dashboard';
import { ResumeUpload } from './features/resume-upload/resume-upload';
import { Ats } from './features/ats/ats';
import { JobDescription } from './features/job-description/job-description';
import { Interview } from './features/interview/interview';
import { History } from './features/history/history';

export const routes: Routes = [
  {
    path: '',
    component: MainLayout,
    children: [
      {
        path: '',
        component: Dashboard
      },
      {
        path: 'resume-upload',
        component: ResumeUpload
      },
      {
        path: 'job-description',
        component: JobDescription
      },
      {
        path: 'ats',
        component: Ats
      },
      {
        path: 'interview',
        component: Interview
      },
      {
        path: 'history',
        component: History
      }
    ]
  }
];