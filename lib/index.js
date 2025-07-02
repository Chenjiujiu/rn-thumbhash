import NativeThumbHash from './NativeThumbHash';
export async function decodeThumbHash(base64Hash) {
    return await NativeThumbHash.decodeThumbHash(base64Hash);
}
export async function encodeThumbHash(base64Image) {
    return await NativeThumbHash.encodeThumbHash(base64Image);
}
