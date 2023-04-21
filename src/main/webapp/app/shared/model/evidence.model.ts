import dayjs from 'dayjs';
import { ITraining } from 'app/shared/model/training.model';
import { StateToDo } from 'app/shared/model/enumerations/state-to-do.model';
import { Kind } from 'app/shared/model/enumerations/kind.model';

export interface IEvidence {
  id?: number;
  id2Trining?: number | null;
  id2Requirents?: number | null;
  id2Course?: number | null;
  id2Employee?: number | null;
  state?: StateToDo | null;
  kind?: Kind | null;
  description?: string;
  note?: string | null;
  expiration?: string | null;
  link?: string | null;
  extra1?: string | null;
  extra2?: string | null;
  extra3?: string | null;
  extra4?: string | null;
  extra5?: string | null;
  extra6?: string | null;
  extra7?: string | null;
  extra8?: string | null;
  extra9?: string | null;
  extra10?: string | null;
  created?: string | null;
  createdAt?: string | null;
  edited?: string | null;
  editedAt?: string | null;
  trainings?: ITraining[] | null;
}

export const defaultValue: Readonly<IEvidence> = {};
