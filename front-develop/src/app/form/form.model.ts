import {User} from '../user/user.model';
import {Course} from '../course/course.model';

export class Form {
  id: number;
  schoolYearStart: number;
  schoolYearEnd: number;
  studyDomain: string;
  semester: number;
  student: User;
  spinneret: object;
  hostEstablishment: string;
  country: string;
  courses: Course[];
  signatureStudent: string;
  signatureDateStudent: Date;
  signatureRpiHome: string;
  signatureDateRpiHome: Date;
  signatureRpiHost: string;
  signatureDateRpiHost: Date;
  closed: boolean;
  createdAt: Date;
  lastModified: Date;
}
