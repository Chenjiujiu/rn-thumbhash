import NativeThumbHash from './NativeThumbHash';
import { LRUCache } from 'lru-cache';

// 内存缓存
const encodeCache = new LRUCache<string, string>({ max: 100 });
const decodeCache = new LRUCache<string, string>({ max: 100 });

export async function decodeThumbHash(base64Hash: string): Promise<string> {
  if (decodeCache.has(base64Hash)) {
    return decodeCache.get(base64Hash)!;
  }
  const result = await NativeThumbHash.decodeThumbHash(base64Hash);
  decodeCache.set(base64Hash, result);
  return result;
}

export async function encodeThumbHash(base64Image: string): Promise<string> {
  if (encodeCache.has(base64Image)) {
    return encodeCache.get(base64Image)!;
  }
  const result = await NativeThumbHash.encodeThumbHash(base64Image);
  encodeCache.set(base64Image, result);
  return result;
}
