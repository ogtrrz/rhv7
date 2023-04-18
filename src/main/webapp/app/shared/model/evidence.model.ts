import dayjs from 'dayjs';
import { ITraining } from 'app/shared/model/training.model';

export interface IEvidence {
  id?: number;
  id2Trining?: number | null;
  id2Requirents?: number | null;
  description?: string;
  expiration?: string | null;
  link?: string | null;
  trainings?: ITraining[] | null;
}

export const defaultValue: Readonly<IEvidence> = {};
