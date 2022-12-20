import * as fs from 'fs';
import { createClient, RedisClientType } from 'redis';

import { Message } from './types';

const path: string = './data';
const fileName: string = 'log.json';

const app = async () => {
  const client: RedisClientType = createClient({
    socket: {
      host: process.env.REDIS_HOST ?? 'localhost',
      port: +(process.env.REDIS_PORT ?? 6379),
    },
  });

  await client.connect();

  if (!fs.existsSync(path)) {
    fs.mkdirSync(path);
  }

  if (!fs.existsSync(`${path}/${fileName}`)) {
    fs.writeFileSync(`${path}/${fileName}`, JSON.stringify({}));
  }

  await client.subscribe('pack_channel', async (message: string) => {
    const data: Message = JSON.parse(message) as Message;

    fs.writeFileSync(
      `${path}/${fileName}`,
      JSON.stringify(
        {
          [`(${data.replicaId}) ${data.time}`]: data,
          ...JSON.parse(fs.readFileSync(`${path}/${fileName}`, 'utf8')),
        },
        null,
        2
      )
    );
  });

  return () => {
    client.disconnect();
  };
};

app().then(() => {
  console.info("[pack's logging service] started!");
});
