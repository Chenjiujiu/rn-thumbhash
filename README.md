## rn-thumbhash


> npm install rn-thumbhash

> cd ios && pod install && cd ..

```javascript
import { decodeThumbHash, encodeThumbHash } from 'rn-thumbhash';
const HomeScreen = memo(() => {
  const [imageUrl, setImageUrl] = useState();
  const thumbHashString = 'NggKFAL4h3eoaHend4gHh3VwWA=='
  useEffect(() => {
    const runThumbHash = async () => {
      const decoded = await decodeThumbHash(thumbHashString);
      setImageUrl(`data:image/png;base64,${decoded}`)
    };
    runThumbHash().catch(console.error);
  }, []);

  return (
    <View>
      <Image source={{ uri: imageUrl }} style={{ width: 100, height: 100 }} />
    </View>
  );
});

```
