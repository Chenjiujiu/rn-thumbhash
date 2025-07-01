import { TurboModule, TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  decodeThumbHash(base64Hash: string): Promise<string>;
  encodeThumbHash(base64Image: string): Promise<string>;
}

export default TurboModuleRegistry.getEnforcing<Spec>('ThumbHashModule');
