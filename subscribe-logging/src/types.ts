export interface Message {
  serviceId: string;
  replicaId: string;
  method: 'GET' | 'POST' | 'PUT' | 'DELETE';
  time: Date;
  request: {
    key: number;
    data: {
      id: number;
      [key: string]: any;
    } | null;
  };
}
