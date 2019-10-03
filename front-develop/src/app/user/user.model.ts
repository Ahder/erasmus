export class User {
  birthdate: Date;
  firstname: string;
  id: number;
  lastname:	string;
  role: string|string[];
  spinneret: {
    id: number;
    label: string,
    level: string
  };
  token: string;
  urlSignature: string;
  password: string;
  enable: boolean;
  email: string;
  resetToken: number;
}
