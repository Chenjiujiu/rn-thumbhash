
Pod::Spec.new do |s|
  s.name         = "rn-thumbhash"
  s.version      = "1.1.0"
  s.summary      = "React Native module for ThumbHash decoding"
  s.license      = "MIT"
  s.homepage     = "#readme"
  s.authors      = "Micas"

  s.platforms    = { :ios => min_ios_version_supported }
  s.source       = { :git => ".git", :tag => "#{s.version}" }

  s.source_files = "ios/**/*.{h,m,mm,swift}"
  s.requires_arc = true
  s.dependency 'React-Core'
  s.swift_version = '5.0'

end
