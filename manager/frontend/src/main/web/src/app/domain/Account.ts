export interface Account {
  id: number;
  hasDevice: boolean;
  switchOff: boolean;
  account: string;
  fio: string;
  phone: string;
  streetName: string;
  house: string;
  letter: string;
  building: string;
  porch: string;
  flat: string;
  connectDate: Date;
  disconnectDate: Date;
  createDate: Date;
  fsb: string;
  gorod: boolean;
  description: string;
}
