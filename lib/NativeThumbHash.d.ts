import { TurboModule } from 'react-native';
export interface Spec extends TurboModule {
    decodeThumbHash(base64Hash: string): Promise<string>;
    encodeThumbHash(base64Image: string): Promise<string>;
}
declare const _default: Spec;
export default _default;
