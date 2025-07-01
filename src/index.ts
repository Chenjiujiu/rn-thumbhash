import NativeThumbHash from './NativeThumbHash';

export async function decodeThumbHash(base64Hash: string): Promise<string> {
  return await NativeThumbHash.decodeThumbHash(base64Hash);
}

export async function encodeThumbHash(base64Image: string): Promise<string> {
  return await NativeThumbHash.encodeThumbHash(base64Image);
}
